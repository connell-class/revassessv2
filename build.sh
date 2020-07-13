gem install travis -y
travis login && travis token
git checkout master && ./ci.sh