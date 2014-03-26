require "install.rb"

VERSION_NUMBER = "1.7-SNAPSHOT"

desc "Tomcat resource adapter for Derby database"
define "derby-tomcat-resource" do
    project.version = VERSION_NUMBER
    project.group = "com.intalio.bpms.derby"
    
    compile.options.source = "1.5"
    compile.options.target = "1.5"
    
    manifest["Implementation-Vendor"] = "Copyright (C) 2010 Intalio inc.  All rights reserved."
    compile.with APACHE_DERBY_NET, TOMCAT_CATALINA
    package(:jar)
end