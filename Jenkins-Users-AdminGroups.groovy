import org.acegisecurity.*
import jenkins.model.Jenkins
import hudson.model.User
import jenkins.security.*
import java.util.Date

User.getAll().each{ u ->
  def prop = u.getProperty(LastGrantedAuthoritiesProperty)
  def realUser = false
  def lastLogin = null
  if (prop) {
    realUser=true
    lastLogin = new Date(prop.timestamp).toString()
  }

  if (realUser){
    println u.getId() + ':' + u.getDisplayName() + '	' + 'lastLogin=' + lastLogin
  } 
}
allUsers = User.getAll()
adminList = []
authStrategy = Jenkins.instance.getAuthorizationStrategy()

for (ua in allUsers) {
    if (authStrategy.hasPermission(ua.toString(), Jenkins.ADMINISTER)) {
        adminList.add(ua)
    }
}
println "Admin groups"
println(adminList)
