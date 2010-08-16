# Hudson rvmRuby Plugin

The hudson rvmRuby plugin makes it easy to run Ruby scripts with a given
ruby interpreter. As a fork of the [Hudson Ruby Plugin](http://wiki.hudson-ci.org/display/HUDSON/Ruby+Plugin),
it makes it simple to run arbitrary ruby code as a test.

## Preview

![rvmRuby in place as a build step](http://img.skitch.com/20100816-9d1nptbdxcsbie6aq7wgnsrft.jpg)

## Installation

Installing this plugin is as simple as uploading the latest compiled
hpi from the [GitHub downloads page](http://github.com/Sutto/hudson-rvmRuby-plugin/downloads).

## Developing

To develop, check out the repository and follow the hudson wiki.
As a general guide,

* `mvn compile` - Builds the code
* `mvn hpi:run` - Starts a test hudson instance
* `mvn hpi:hpi` - Builds a runnable hpi

## Contributing

We encourage all community contributions. Keeping this in mind, please follow these general guidelines when contributing:

* Fork the project
* Create a topic branch for what you’re working on (git checkout -b awesome_feature)
* Commit away, push that up (git push your\_remote awesome\_feature)
* Create a new GitHub Issue with the commit, asking for review. Alternatively, send a pull request with details of what you added.
* Once it’s accepted, if you want access to the core repository feel free to ask! Otherwise, you can continue to hack away in your own fork.

Other than that, our guidelines very closely match the GemCutter guidelines [here](http://wiki.github.com/qrush/gemcutter/contribution-guidelines).

(Thanks to [GemCutter](http://wiki.github.com/qrush/gemcutter/) for the contribution guide)


