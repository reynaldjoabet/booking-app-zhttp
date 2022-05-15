import appConfig.AppConfig
import zhttp.http._
import zhttp.service.Server
import zio.Console.{printLine, readLine}
import zio._
import db.QuillContext
import zhttp.http.middleware.Auth

object Main extends ZIOAppDefault  {
 trait animal
  class Dog() extends  animal
  val k:(Nothing, Dog) => animal = (v1: String, a:animal) => new Dog()

  Auth
  override def run: ZIO[ZIOAppArgs with Scope, Any, Any] = (for {
    serverConfig <- ZIO.serviceWith[AppConfig](_.serverConfig)
       // _        <- QuillContext.migrate
    serverFiber <- Server.start(serverConfig.port,  Http.notFound)
      .forkDaemon
    - <- printLine("Press Any Key to stop the  server\n") *> readLine.catchAll(e =>
      printLine(s"There was an error! ${e.getMessage}\n") *>
        printLine("Shutting down  server and consumer")
    ) *> serverFiber.interrupt
  } yield ()
    ).provide(appConfig.live).orDie
}
