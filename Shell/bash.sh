#!/bin/bash
# '-C' option avoids overwriting already existing files.

# '-e' option avoids executing the following commands
#if one command fails (equals when the status code is 0).
# Attach '&& true' after a command if you want to continue
#command executions after its failure.

# '-u' option avoids evaluating a non-existing variable as
#an empty string.
set -Ceu

# 'test' is a conditional judgement command.
#The result of judgement is stored to '$?'. 0 means True.

## arithmetic (operators: -eq, -ne, -lt, -le, -gt, -ge)
state="test 0 -eq 1"
echo "\$?'s value after '${state}' was executed."
${state} && true ; echo $?

## string (operators: =, !=)
string1="abc"
string2="abcd"
state="test $string1 = $string2"
echo "\$?'s value after '${state}' was executed."
${state} && true ; echo $?

## file (binary operators: -nt, -ot.
##       unary operators: -z, -n , -d, -f, -s, -e, -r, -w, -x)
file1="hoge1.txt"
file2="hoge2.txt"
touch $file1
sleep 1s
touch $file2
state="test $file1 -nt $file2"
echo "\$?'s value after '${state}' was executed."
${state} && true ; echo $?

# If statement
int=0
if [ $int = 0 ] ; then
    echo "'int' is zero."
else
    echo "'int' is not zero."
fi

# For statement
## explicit loop
for i in 1 2 3 4 5
    do
        echo "$i"
    done

## explict loop by abbreaviation
for i in $(seq 1 100)
    do
        echo "$i"
    done