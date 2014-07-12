#!/bin/sh -x
# get branch name or tag name
function getBranchOrTagName(){
    local commitId=`git show | head -n 1 | cut -d " " -f 2`
    if [ -z "$commitId" ] ; then
        echo "no commit id found!"
        exit 1
    fi
    local branOrTagName="";
    git tag >/tmp/tag.txt
    while read TAG
    do
        git show $TAG | grep $commitId >/dev/null 2>&1
        if [ $? -eq 0 ] ; then
            branOrTagName="$TAG"
            break;
        fi
    done < /tmp/tag.txt

    if [ -n "$branOrTagName" ] ; then
        echo "$branOrTagName"
        exit 0
    fi

    git branch > /tmp/branch.txt
    while read BRANCH
    do

     BRANCH=`echo "$BRANCH" | sed -e 's/*//g' -e 's/ //g'`

        git show "$BRANCH" | head -n 1 | grep $commitId >/dev/null 2>&1
        if [ $? -eq 0 ] ; then
            branOrTagName="$BRANCH"
            break;
        fi
    done < /tmp/branch.txt

    if [ -z "$branOrTagName" ] ; then
        echo "no human name for commit id $commitId!"
        exit 1
    fi

    echo "$branOrTagName"
    exit 0
}
