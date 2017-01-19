#!/bin/sh

gradle :library:assemble publishToMavenLocal :library:artifactorypublish
