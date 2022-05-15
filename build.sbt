ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"


val logbackVersion= "1.2.11"
val zhttpVersion= "2.0.0-RC7"
val zioJsonVersion="0.1.5"
val zioConfigVersion   = "3.0.0-RC9"
val zioVersion         = "2.0.0-RC3"
val jwtCoreVersion="9.0.5"
val postgresVersion = "42.3.4"
val flywayVersion      = "8.5.10"
val quillVersion="3.17.0-RC2"

val jwtDependencies=Seq(
  "com.github.jwt-scala" %% "jwt-core" % jwtCoreVersion
)
val postgresDependencies=Seq(
  "org.postgresql"   % "postgresql" % postgresVersion
)
val quillDependencies= Seq(
  "io.getquill"     %% "quill-jdbc-zio"  % quillVersion
  //"com.h2database" % "h2" % h2Version
)

val flywayDependencies=Seq(
  "org.flywaydb"     % "flyway-core"         % flywayVersion
)

val zioDependencies= Seq(
  "dev.zio"         %% "zio"  %zioVersion
)
val loggingDependencies= Seq(
  "ch.qos.logback" % "logback-classic" % logbackVersion
)

val jsonDependencies=Seq(
  "dev.zio" %% "zio-json" % zioJsonVersion
)

val zioConfigDependencies=Seq(
  "dev.zio"         %% "zio-config"          % zioConfigVersion,
  "dev.zio"         %% "zio-config-typesafe" % zioConfigVersion,
  "dev.zio"         %% "zio-config-magnolia" % zioConfigVersion
)


val httpDependencies= Seq(
  "io.d11"          %% "zhttp" %zhttpVersion
)

scalacOptions ++= Seq(
  "-Ylog-classpath",
  "-encoding", "utf8",
  // Option and arguments on same line
  "-Xfatal-warnings",
  // New lines for each options
  "-deprecation",
  "-unchecked",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps"
)



lazy val root = (project in file("."))
  .settings(
    name := "booking-app-zhttp",
    libraryDependencies ++= jsonDependencies ++ loggingDependencies ++ flywayDependencies
      ++httpDependencies ++zioDependencies ++ zioConfigDependencies ++
      postgresDependencies ++ jwtDependencies //++ quillDependencies
  )
