package service

import java.time.Clock
import zio.json._
import zio._
import pdi.jwt.{Jwt,JwtAlgorithm,JwtClaim}

trait AuthenticationService {
   def jwtDecode(jwt:String): Task[JwtClaim]
   def jwtEncode(value:String): Task[String]
}


private[service] final case class AuthenticationServiceLive() extends AuthenticationService{
    // Secret Authentication key
    private val SECRET_KEY = "secretKey"
    private  implicit val clock: Clock = Clock.systemUTC
    override def jwtDecode(jwt: String):Task[JwtClaim]=
    ZIO.fromOption(Jwt.decode(jwt,SECRET_KEY, Seq(JwtAlgorithm.HS512)).toOption)
      .mapError(_ => new Exception())

    override def jwtEncode(value: String): Task[String] = {
    val json  = {"username:"+value}
    val claim = JwtClaim { json }.issuedNow.expiresIn(60)
    ZIO.attempt(Jwt.encode(claim, SECRET_KEY, JwtAlgorithm.HS512)  )

            }

}

object  AuthenticationService {
    val live: URLayer[Any,AuthenticationServiceLive]= ZLayer.fromFunction(AuthenticationServiceLive.apply _)

    // Secret Authentication key
       private val SECRET_KEY = "secretKey"
       private  implicit val clock: Clock = Clock.systemUTC
        def jwtDecodes(jwt: String): Option[JwtClaim]=
       Jwt.decode(jwt,SECRET_KEY, Seq(JwtAlgorithm.HS512)).toOption
         

        def jwtEncodes(value: String): String = {
       val json  = {"username:"+value}
       val claim = JwtClaim { json }.issuedNow.expiresIn(60)
          println(claim)
       Jwt.encode(claim, SECRET_KEY, JwtAlgorithm.HS512)      

               }


  def jwtDecode(jwt:String): ZIO[AuthenticationService, Throwable, JwtClaim] =ZIO.serviceWithZIO[AuthenticationService](_.jwtDecode(jwt))
  def jwtEncode(value:String): ZIO[AuthenticationService, Throwable, String] = ZIO.serviceWithZIO[AuthenticationService](_.jwtEncode(value))
}