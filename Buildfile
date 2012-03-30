VERSION_NUMBER = "1.5-SNAPSHOT"

repositories.remote << "http://www.ibiblio.org/maven2/"
repositories.remote << "http://repo1.maven.org//maven2/"

#repositories.release_to[:username] ||= "release"
#repositories.release_to[:url] ||= "sftp://www.intalio.org/var/www-org/public/maven2"
#repositories.release_to[:permissions] ||= 0664

repositories.release_to[:url] ||= "sftp://release@release.intalio.com/home/release/m2repo"
repositories.release_to[:password] ||= "release"

DERBY = "org.apache.derby:derbynet:jar:10.8.2.2"
TOMCAT = "org.apache.tomcat:catalina:jar:6.0.35"

desc "Tomcat resource adapter for Derby database"
define "derby-tomcat-resource" do
    project.version = VERSION_NUMBER
    project.group = "com.intalio.bpms.derby"
    compile.options.target = "1.5"
    manifest["Implementation-Vendor"] = "Copyright (C) 2010 Intalio inc.  All rights reserved."
    compile.with DERBY, TOMCAT
    package(:jar)
end
