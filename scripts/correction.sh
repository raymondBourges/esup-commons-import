#!/bin/bash
if [ $# == 0 ]; then
  echo "Usage: $0 <exercice>"
  echo "  Exemples: $0 exo4"
  echo "            $0 raz"
  exit 1
fi

REPO_SERVLET_FORMATION="https://subversion.cru.fr/esup-commons/branches/esup-formation-correction/esup-formation"
REPO_PORTLET_FORMATION="https://subversion.cru.fr/esup-commons/branches/esup-formation-correction/esup-portlet-formation"
VERSION_EC2="0.2.8"

P1="esup-formation"
P2="esup-portlet-formation"
PROJECTDIR="$HOME/workspace"
CURDIR="`dirname $0`"
SELF_PATH=`cd -P $CURDIR && pwd -P`
SELF_PATH="$SELF_PATH/`basename $0`"
EXO="`echo $1 | tr [:upper:] [:lower:]`"

CORRECTIONDIR="`dirname $SELF_PATH`/correctionFormationEC2"
ORIGINAUXDIR="`dirname $SELF_PATH`/correctionFormationEC2/originaux"


if [ ! -d "$CORRECTIONDIR" ]; then
  mkdir -p "$CORRECTIONDIR"
  cd "$CORRECTIONDIR"
  echo  " > Recupération des corrections depuis les dépots SVN"
  svn checkout $REPO_SERVLET_FORMATION >> /dev/null
  svn checkout $REPO_PORTLET_FORMATION >> /dev/null
  cd $OLDPWD
  find "$CORRECTIONDIR" -type d -name ".svn" -print0 | xargs -0 /bin/rm -rf
fi

if [ $1 == "raz" ]; then
  if [ ! -d "$ORIGINAUXDIR" ]; then
    mkdir -p "$ORIGINAUXDIR"
    cd "$ORIGINAUXDIR"
    echo  " > Création des projets pour RAZ"
    mvn archetype:generate -B -DarchetypeRepository=https://mvn.esup-portail.org/content/repositories/releases -DarchetypeGroupId=org.esupportail.blank -DarchetypeArtifactId=esup-blank-archetype -DarchetypeVersion=$VERSION_EC2 -DgroupId=org.esupportail.formation -DartifactId=esup-formation -Dpackage=org.esupportail.formation -Dversion=0.0.1-SNAPSHOT >> /dev/null
    mvn archetype:generate -B -DarchetypeRepository=https://mvn.esup-portail.org/content/repositories/releases -DarchetypeGroupId=org.esupportail.blank -DarchetypeArtifactId=esup-blank-archetype -DarchetypeVersion=$VERSION_EC2 -DgroupId=org.esupportail.formation -DartifactId=esup-portlet-formation -Dpackage=org.esupportail.formation.portlet -Dversion=0.0.1-SNAPSHOT >> /dev/null
    cd $OLDPWD
  fi
  find "$PROJECTDIR/$P1" -type f  ! -name ".*" -print0 | xargs -0 /bin/rm -f
  find "$PROJECTDIR/$P2" -type f  ! -name ".*" -print0 | xargs -0 /bin/rm -f
  for i in `find "$ORIGINAUXDIR" -type f`; do
    old=${i//$ORIGINAUXDIR/$PROJECTDIR}
    dirold="`dirname $old`"
    echo "Copie de \"$i\" vers \"$old\""
    if [ ! -d "$dirold" ]; then
      mkdir -p "$dirold"
    fi
    cp -f $i $old
  done
else
  for i in `find "$CORRECTIONDIR" -type f -name "*-$EXO"`; do
    dircorrection=`dirname $i`
    correction=`basename $i`
    c_name=${correction/.*-*/}
    c_ext=${correction/$c_name/}
    c_ext=${c_ext/-*/}
    correction="$c_name$c_ext"
    correction="$dircorrection/$correction"
    old=${correction//$CORRECTIONDIR/$PROJECTDIR}
    dirold="`dirname $old`"
    echo "Copie de \"$i\" vers \"$old\""
    if [ ! -d "$dirold" ]; then
      mkdir -p "$dirold"
    fi
    cp -f $i $old
  done
fi

