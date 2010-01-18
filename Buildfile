VERSION_NUMBER = "1.4-SNAPSHOT"

repositories.remote << "http://www.ibiblio.org/maven2/"

repositories.release_to[:username] ||= "release"
repositories.release_to[:url] ||= "sftp://release.intalio.com/home/release/m2repo"
repositories.release_to[:password] ||= "release"

DERBY = "org.apache.derby:derbynet:jar:10.4.2.0"
TOMCAT = "tomcat:catalina:jar:5.5.9"

desc "Tomcat resource adapter for Derby database"
define "derby-tomcat-resource" do
    project.version = VERSION_NUMBER
    project.group = "com.intalio.bpms.derby"
    compile.options.target = "1.5"
    manifest["Implementation-Vendor"] = "Copyright (C) 2010 Intalio inc.  All rights reserved."
    compile.with DERBY, TOMCAT
    package(:jar)
end
