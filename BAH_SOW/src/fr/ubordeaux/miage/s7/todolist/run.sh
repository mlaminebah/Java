#!/bin/sh
prog=$1
PATH_TO_FX='/home/minesabry/Bureau/openjfx-17.0.1_linux-aarch64_bin-sdk/javafx-sdk-17.0.1/lib'
javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml $prog
prog=${prog%.java}
java --module-path $PATH_TO_FX --add-modules javafx.controls $prog
rm *.class model/*.class view/*.class controller/*.class