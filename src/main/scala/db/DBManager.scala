/*
package db
import model._
import zio.{Task,ZLayer}



trait  DBManager{
    def addUser(user:String): Task[Unit]
    def getUserByEmail(email:String): Task[String]
    def getUserById(id:Int): Task[String]

}

final  case class DBManagerLive() extends DBManager{
    import QuillContext._
    override def addUser(user: String): Task[Unit] = ???
    
    override def getUserByEmail(email: String): Task[String] = ???
    
    override def getUserById(id: Int): Task[String] = ???
    
    /*
    override def addUser(user: String): Task[Unit] =
        run(query[String].insertValue(lift(user))).unit
        .provide(dataSourceLayer)// can fail with SQLException
        .mapError(e=> Error.InternalServerError(e.getMessage))


    
    override def getUserByEmail(email: String): Task[String] = ???
        //run(query[String].filter(s=> s==lift(email)))
        //map(_.headOption)
        //.provide(dataSourceLayer)
        //.mapError( e=>Error.InternalServerError(e.getMessage))
        //.collect(Error.NotFound("User not found")){case Some(u)=>u}

    override def getUserById(id: Int): Task[String] =  ???
           //run(query[User].filter(user=>user.id== lift(id)))
           //.map(_.headOption)
           //.provide(dataSourceLayer)
           //.mapError( e=>Error.InternalServerError(e.getMessage))
           //.collect(Error.NotFound("User not found")){case Some(u)=>u}
           */
}


object DBManager  {
    val live: ZLayer[Any, Nothing, DBManagerLive] = ZLayer.fromFunction(DBManagerLive.apply _)
  
}



 */