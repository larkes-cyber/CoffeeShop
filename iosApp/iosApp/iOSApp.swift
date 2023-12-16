import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init(){
        BusinessModule().doInit()
    }
    
	var body: some Scene {
        
		WindowGroup {
            TabView {

                StartScreen()
                    .tabItem {
                        Image(systemName: "heart.fill")
                        Text("Favourites")
                }
                
                SplashScreen()
                    .tabItem {
                        Image(systemName: "person.fill")
                        Text("Friends")
                }
            }
		}
	}
}
