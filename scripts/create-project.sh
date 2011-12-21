mvn archetype:generate -B -DarchetypeRepository=https://mvn.esup-portail.org/content/repositories/releases -DarchetypeGroupId=org.esupportail.blank -DarchetypeArtifactId=esup-blank-archetype -DarchetypeVersion=0.2.8 -DgroupId=org.esupportail.formation -DartifactId=esup-formation -Dpackage=org.esupportail.formation -Dversion=0.0.1-SNAPSHOT

cd esup-formation
mvn eclipse:eclipse

