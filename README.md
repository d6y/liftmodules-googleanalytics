# Google Analytics Lift Module

Inserts the [Google Analytics async tracking code](http://code.google.com/apis/analytics/docs/tracking/asyncTracking.html) into a [Lift](http://www.liftweb.net) application.

That is, if you supply a Google Analytics tracking ID, the head of all pages will have the Google tracking Javascript code automatically added.


## Using this module

1. Add the following repository to your SBT project file:

    For SBT 0.11:

        resolvers += "Sonatype" at "https://oss.sonatype.org/content/repositories/public/"

    For SBT 0.7:

        lazy val liftModulesRelease =  "Sonatype" at "https://oss.sonatype.org/content/repositories/public/"

2. Include this dependency:

         "net.liftmodules" %% "google-analytics" % (liftVersion+"-1.0")

3. In your application's Boot.boot code:

          bootstrap.liftmodules.GoogleAnalytics.init

4. Finally, set your tracking code as the `google.analytics.id` in your Props file.  For example, add the following to `src/main/resources/production.default.props`

         google.analytics.id=UA-XXXXXXXX-X

    ...obviously replacing `XXXXXXX-X` with the code Google issued you with.  Be sure to start your Lift app with `-Drun.mode=production` flag (or set the value of google.analytics.id in your dev props file).  

