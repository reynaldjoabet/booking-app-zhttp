import zio.TaskLayer
import zio.config.ConfigDescriptor._
import zio.config.magnolia.descriptor
import zio.config.syntax._
import zio.config.typesafe.TypesafeConfig
import zio.{Layer, ZLayer}
import zio.config.ReadError
package object appConfig {

  type AllConfig=AppConfig  with ServerConfig with FlywayConfig

  final val Root: String = "booking-app"

  private final val Descriptor: _root_.zio.config.ConfigDescriptor[AppConfig] =descriptor[AppConfig]

  private val appConfig: Layer[ReadError[String], AppConfig] = TypesafeConfig.fromResourcePath(nested(Root)(Descriptor))
//TaskLayer[AllConfig]
  val live: ZLayer[Any, ReadError[String], AllConfig] =
    appConfig >+>
      appConfig.narrow(_.serverConfig) >+>
      appConfig.narrow(_.flywayConfig)
}


