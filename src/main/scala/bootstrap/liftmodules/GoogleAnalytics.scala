package bootstrap.liftmodules

import net.liftweb.util.Props
import net.liftweb.http._

import net.liftmodules.googleanalytics.Async

object GoogleAnalytics {
	
	def init : Unit = Props.get("google.analytics.id") map Async.headJs foreach { js =>
		def addTracking(s: LiftSession, r: Req) : Unit = S.putInHead(js)
		LiftSession.onBeginServicing = addTracking _ :: LiftSession.onBeginServicing
	}
	
}
