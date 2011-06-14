# Google Analytics Lift Module

Inserts the [Google Analytics async tracking code](http://code.google.com/apis/analytics/docs/tracking/asyncTracking.html) into a [Lift](http://www.liftweb.net) application.


## Using this module

Add the following repository to your SBT project file:

	lazy val liftModulesRelease = "liftmodules repository" at "http://repository-liftmodules.forge.cloudbees.com/release/"

And then include this dependency:

	"net.liftmodules" %% "google-analytics" % (liftVersion+"-0.9")

In your application's Boot.boot code:

	bootstrap.liftmodules.GoogleAnalytics.init

Finally, set your tracking code as the google.analytics.id in your Props file.  For example, add the following to src/main/resources/production.default.props

	google.analytics.id=UA-XXXXXXXX-X

...obviously replacing XXXXXXX-X with the code Google issued you with.  Be sure to start your Lift app with -Drun.mode=production flag (or set the value of google.analytics.id in your dev props file).  

## Supported Scala and Lift versions

Scala 2.8.1 and 2.9.0-1: Lift 2.4-RC1

Scala 2.8.1 only: Lift 2.3
