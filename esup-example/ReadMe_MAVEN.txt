LANCER à la racine du projet
mvn archetype:create -DgroupId=org.esupportail -DartifactId=esup-blank -DpackageName=org.esupportail.blank
cd esup-blank
puis : mvn eclipse:eclipse
Dans eclipse --> import du projet
Ajout de la variable M2_REPO --> vers homedir/.m2/repository/

List archetype --> http://docs.codehaus.org/display/MAVENUSER/Archetypes+List

Note :
recherche archetypes --> http://mvnrepository.com/
exemple appfuse --> http://appfuse.org/display/APF/AppFuse+QuickStart
regarder :
	<plugin>
		<groupId>org.codehaus.mojo</groupId>
        <artifactId>hibernate3-maven-plugin</artifactId>
        <version>2.2</version>
