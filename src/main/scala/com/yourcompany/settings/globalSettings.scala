package com.yourcompany.settings

import com.huemulsolutions.bigdata.common._
import scala.collection.mutable._
import scala.io.Source
import java.io.{FileNotFoundException, IOException}
import com.databricks.dbutils_v1.DBUtilsHolder.dbutils


/**
 * Configuración del ambiente
 */
object globalSettings {
   val Global: huemul_GlobalPath  = new huemul_GlobalPath()
   Global.GlobalEnvironments = "production, experimental"
   
   /**
   * Get encrypted key from file, and return decrypted key. 
   */
  def getKeyFromFile(fileName: String): String = {
    var key: String = null
    
   try {
      val openFile = Source.fromFile(fileName)
      key = openFile.getLines.mkString
      openFile.close()
    } catch {
        case e: FileNotFoundException => println(s"Couldn't find that file: ${fileName}")
        case e: IOException => println(s"(${fileName}). Got an IOException! ${e.getLocalizedMessage}")
        case e: Exception => println(s"exception opening ${fileName}")
    }
    
    return key
  }
   

   val localPath: String = System.getProperty("user.dir").concat("/")
   println(s"path: ${localPath}")

   
   //para ejemplo sobre databricks
   Global.setBigDataProvider( huemulType_bigDataProvider.databricks)
   val baseDir = "/mnt/huemul/data"
   
   Global.HIVE_HourToUpdateMetadata =50

   val lControlConnectionString = dbutils.secrets.get(scope = "huemul-test-secret-scope", key = "production-demo-setting-control-connection")
   Global.CONTROL_Setting.append(new huemul_KeyValuePath("production",lControlConnectionString))
   //Global.CONTROL_Setting.append(new huemul_KeyValuePath("experimental",getKeyFromFile(s"${localPath}prod-demo-setting-control-connection.set")))
   
   Global.ImpalaEnabled = false
   val lImpalaConnectionString = dbutils.secrets.get(scope = "huemul-test-secret-scope", key = "production-demo-setting-impala-connection")
   Global.IMPALA_Setting.append(new huemul_KeyValuePath("production",lImpalaConnectionString))
   //Global.IMPALA_Setting.append(new huemul_KeyValuePath("experimental",getKeyFromFile(s"${localPath}prod-demo-setting-impala-connection.set")))

   //FIN DATABRICKS

   
   /*
    * val baseDir = "/user/data"
   Global.HIVE_HourToUpdateMetadata =50
   Global.CONTROL_Setting.append(new huemul_KeyValuePath("production",getKeyFromFile(s"${localPath}prod-demo-setting-control-connection.set")))
   Global.CONTROL_Setting.append(new huemul_KeyValuePath("experimental",getKeyFromFile(s"${localPath}prod-demo-setting-control-connection.set")))
   
   Global.ImpalaEnabled = false
   Global.IMPALA_Setting.append(new huemul_KeyValuePath("production",getKeyFromFile(s"${localPath}prod-demo-setting-impala-connection.set")))
   Global.IMPALA_Setting.append(new huemul_KeyValuePath("experimental",getKeyFromFile(s"${localPath}prod-demo-setting-impala-connection.set")))
*/
   //from 2.3
   //val HIVE_Setting = new ArrayBuffer[huemul_KeyValuePath]()
   //HIVE_Setting.append(new huemul_KeyValuePath("production",getKeyFromFile(s"${localPath}prod-demo-setting-hive-connection.set")))
   //HIVE_Setting.append(new huemul_KeyValuePath("experimental",getKeyFromFile(s"${localPath}prod-demo-setting-hive-connection.set")))

   //from 2.3
   //Global.externalBBDD_conf.Using_HIVE.setActive(true).setActiveForHBASE(true).setConnectionStrings(HIVE_Setting)
   
   
   //TEMPORAL SETTING
   Global.TEMPORAL_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/temp/"))
   Global.TEMPORAL_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/temp/"))
     
   //RAW SETTING
   Global.RAW_SmallFiles_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/raw/"))
   Global.RAW_SmallFiles_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/raw/"))
   
   Global.RAW_BigFiles_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/raw/"))
   Global.RAW_BigFiles_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/raw/"))
   
   
   
   //MASTER SETTING
   Global.MASTER_DataBase.append(new huemul_KeyValuePath("production","production_master"))   
   Global.MASTER_DataBase.append(new huemul_KeyValuePath("experimental","experimental_master"))

   Global.MASTER_SmallFiles_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/master/"))
   Global.MASTER_SmallFiles_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/master/"))
   
   Global.MASTER_BigFiles_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/master/"))
   Global.MASTER_BigFiles_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/master/"))

   //DIM SETTING
   Global.DIM_DataBase.append(new huemul_KeyValuePath("production","production_dim"))   
   Global.DIM_DataBase.append(new huemul_KeyValuePath("experimental","experimental_dim"))

   Global.DIM_SmallFiles_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/dim/"))
   Global.DIM_SmallFiles_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/dim/"))
   
   Global.DIM_BigFiles_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/dim/"))
   Global.DIM_BigFiles_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/dim/"))

   //ANALYTICS SETTING
   Global.ANALYTICS_DataBase.append(new huemul_KeyValuePath("production","production_analytics"))   
   Global.ANALYTICS_DataBase.append(new huemul_KeyValuePath("experimental","experimental_analytics"))
   
   Global.ANALYTICS_SmallFiles_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/analytics/"))
   Global.ANALYTICS_SmallFiles_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/analytics/"))
   
   Global.ANALYTICS_BigFiles_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/analytics/"))
   Global.ANALYTICS_BigFiles_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/analytics/"))

   //REPORTING SETTING
   Global.REPORTING_DataBase.append(new huemul_KeyValuePath("production","production_reporting"))
   Global.REPORTING_DataBase.append(new huemul_KeyValuePath("experimental","experimental_reporting"))

   Global.REPORTING_SmallFiles_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/reporting/"))
   Global.REPORTING_SmallFiles_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/reporting/"))
   
   Global.REPORTING_BigFiles_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/reporting/"))
   Global.REPORTING_BigFiles_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/reporting/"))

   //SANDBOX SETTING
   Global.SANDBOX_DataBase.append(new huemul_KeyValuePath("production","production_sandbox"))
   Global.SANDBOX_DataBase.append(new huemul_KeyValuePath("experimental","experimental_sandbox"))
   
   Global.SANDBOX_SmallFiles_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/sandbox/"))
   Global.SANDBOX_SmallFiles_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/sandbox/"))
   
   Global.SANDBOX_BigFiles_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/sandbox/"))
   Global.SANDBOX_BigFiles_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/sandbox/"))
   
   //DQ_ERROR SETTING
   Global.DQ_SaveErrorDetails = true
   Global.DQError_DataBase.append(new huemul_KeyValuePath("production","production_DQError"))
   Global.DQError_DataBase.append(new huemul_KeyValuePath("experimental","experimental_DQError"))
   
   Global.DQError_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/dqerror/"))
   Global.DQError_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/dqerror/"))

   //OLD VALUE TRACE
   Global.MDM_SaveOldValueTrace = true
   Global.MDM_OldValueTrace_DataBase.append(new huemul_KeyValuePath("production","production_mdm_oldvalue"))
   Global.MDM_OldValueTrace_DataBase.append(new huemul_KeyValuePath("experimental","experimental_mdm_oldvalue"))
   
   Global.MDM_OldValueTrace_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/mdm_oldvalue/"))
   Global.MDM_OldValueTrace_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/mdm_oldvalue/"))

   //BACKUP
   Global.MDM_Backup_Path.append(new huemul_KeyValuePath("production",s"${baseDir}/production/backup/"))
   Global.MDM_Backup_Path.append(new huemul_KeyValuePath("experimental",s"${baseDir}/experimental/backup/"))


   //HBase
   Global.setHBase_available()
}

