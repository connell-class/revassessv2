git config --global pull.rebase false
test(){
# iterate over first two tiers
    for i in 1 2
    do
        git checkout tier$i
        git pull
        cd ./Revassess/
        pass="`mvn clean test -Dtest=Tier${i}Tests`"
        exitCode="`echo $pass | grep PointsTests | grep -c FAILURE`"
      	tierPoints="`echo $pass | grep -oE '_points:[0-9]+' | grep -Eo '[0-9]+'`"
        points="`expr $points + $tierPoints`"
        mvn clean
    # if a tier failed, break and report
        if (( $exitCode < 0 ))
            then
                failedTier=$i
                break
        fi
        cd ../
    done
    echo done with tiers 1 and 2
    # exit previous loop and check variable for if assessment failed
    #if [ $exitCode -eq 0 ]
    if [ 1 -eq 1 ]
        then
            git checkout tiers3456
            git pull
            cd ./Revassess/
            for j in 3 4 5 6
            do
                    pass="`mvn clean test -Dtest=Tier${i}Tests`"
                    exitCode="`echo $pass | grep PointsTests | grep -c FAILURE`"
                    tierPoints="`echo $pass | grep -oE '_points:[0-9]+' | grep -Eo '[0-9]+'`"
                    echo points $points and tier points $tierPoints
                    points="`expr $points + $tierPoints`"
                    if (( $exitCode > 0 ))
                        then
                            failedTier=$j
                            break
                    fi
                    cd ../
            done
    fi
    git checkout master
}
test
if [ $failedTier > 0 ]
then
    echo the failed tier was: tier $failedTier
    echo the total number of points are $points
    exit 100
else
    echo revassess has been passed
    echo the total number of points received is $points
fi
