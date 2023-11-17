import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init(){
        BusinessModule().doInit()
    }
    
	var body: some Scene {
        
		WindowGroup {
			ContentView()
		}
	}
}
