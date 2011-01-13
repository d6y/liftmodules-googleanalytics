# Google Analytics Lift Module

Inserts the [Google Analytics async tracking code](http://code.google.com/apis/analytics/docs/tracking/asyncTracking.html) into a [Lift](http://www.liftweb.net) application.

## Using this module

There is currently no public repository containing this module, so for now you'll need to compile it yourself :-(

$ git clone
$ cd
$ sbt
update
publish-local

Once published, add the following dependency to your SBT project file:

	"net.liftmodules" %% "google-analytics" % "0.8"

In your application's Boot.boot code:

	bootstrap.liftmodules.GoogleAnalytics.init

Set your tracking code as the google.analytics.id in your Props file.  For example, add the following to src/main/resources/production.default.props

	google.analytics.id=UA-XXXXXXXX-X

...obviously replacing XXXXXXX-X with the code Google issued you with.  Be sure to start your Lift app with -Drun.mode=production flag (or set the value in your dev props file).




