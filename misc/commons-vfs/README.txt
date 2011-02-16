Pour esup-portlet-stockage, nous avons déployé le tag 1.0 de commons-vfs dans le maven esup ; le but étant de pouvoir disposer en maven du sandbox de VFS.

Pour ce faire nous avons récupéré le tag vfs-1.0 en subversion depuis cette url :
http://svn.apache.org/repos/asf/commons/proper/vfs/tags/vfs-1.0
Nous avons ensuite modifié les pom.xml pour avoir une version nommée svn-tag-vfs-1.0 cf le fichier commons-vfs.diff et pour préciser que le distributionManagement devait utiliser le repository esup https://mvn.esup-portail.org/content/repositories/thirdparty/.

Nous avons ensuite fait un mvn deploy - notez que pour que les tests passent (et ainsi déployer également le test-jar de commons-fs) nous avons dû nous y reprendre à 2 fois : 
Nous avons en effet fait dans l'ordre un 
mvn install 
cp -rf ./core/target/classes/test-data ./core/target/test-classes/
mvn deploy.
