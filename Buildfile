repositories.remote << "http://www.ibiblio.org/maven2/"
repositories.remote << "http://repo1.maven.org//maven2/"

#repositories.release_to[:username] ||= "release"
#repositories.release_to[:url] ||= "sftp://www.intalio.org/var/www-org/public/maven2"
#repositories.release_to[:permissions] ||= 0664

repositories.release_to[:url] ||= "sftp://release@release.intalio.com/home/release/m2repo"
repositories.release_to[:password] ||= "release"

VERSION_NUMBER = "1.6-SNAPSHOT"
DP_VERSION_NUMBER="1.0.1"

if ENV['DP_VERSION_NUMBER']
DP_VERSION_NUMBER = "#{ENV['DP_VERSION_NUMBER']}"
end

# We need to download the artifact before we load the same
artifact("org.intalio.common:dependencies:rb:#{DP_VERSION_NUMBER}").invoke

DEPENDENCIES = "#{ENV['HOME']}/.m2/repository/org/intalio/common/dependencies/#{DP_VERSION_NUMBER}/dependencies-#{DP_VERSION_NUMBER}.rb"
if ENV["M2_REPO"]
DEPENDENCIES ="#{ENV['M2_REPO']}/org/intalio/common/dependencies/#{DP_VERSION_NUMBER}/dependencies-#{DP_VERSION_NUMBER}.rb"
end
puts "Loading #{DEPENDENCIES}"
load DEPENDENCIES


desc "Tomcat resource adapter for Derby database"
define "derby-tomcat-resource" do
    project.version = VERSION_NUMBER
    project.group = "com.intalio.bpms.derby"
    compile.options.target = "1.5"
    manifest["Implementation-Vendor"] = "Copyright (C) 2010 Intalio inc.  All rights reserved."
    compile.with APACHE_DERBY_NET, TOMCAT_CATALINA
    package(:jar)
end
