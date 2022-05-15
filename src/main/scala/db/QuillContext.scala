package db

import appConfig._
//import io.getquill.{PostgresZioJdbcContext,SnakeCase}
import javax.sql.DataSource
//import io.getquill.context.ZioJdbc.DataSourceLayer
import zio.{Task,ZLayer,ZIO}
import org.flywaydb.core.Flyway
//extends PostgresZioJdbcContext(SnakeCase)
object QuillContext  {
   //val dataSourceLayer: ZLayer[Any,Nothing,DataSource]=DataSourceLayer.fromPrefix("postgres").orDie

   def migrate: ZIO[FlywayConfig,Throwable,Unit]=ZIO.serviceWithZIO[FlywayConfig]{config=>
      for{
         flyway<-ZIO.attempt(Flyway.configure().dataSource(config.url,config.username,config.password).load())
             _ <- ZIO.attempt(flyway.migrate())
      } yield ()

   }
}
