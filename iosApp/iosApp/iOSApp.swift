import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init(){
        BusinessModule().doInit()
    }
    
    @State private var tabState: Visibility = .hidden

    
	var body: some Scene {
        
		WindowGroup {
            TabView {
                NavigationView{
                    SplashScreen()
                }
                .phoneOnlyStackNavigationView()
                .tabItem {
                    Image(systemName: "house.fill")
                }
                NavigationView{
                    FavoriteScreen()
                }
                .phoneOnlyStackNavigationView()
                .tabItem {
                    Image(systemName: "heart.fill")
                }
                NavigationView(){
                    CartScreen()
                        
                }
                .phoneOnlyStackNavigationView()
                .tabItem {
                    Image(systemName: "bag")
                }
                NavigationView(){
                    UserScreen()
                }
                .phoneOnlyStackNavigationView()
                .tabItem {
                    Image(systemName: "person.crop.circle")
                }
                
            }
		}
	}
}

extension UIApplication {
    var key: UIWindow? {
        self.connectedScenes
            .map({$0 as? UIWindowScene})
            .compactMap({$0})
            .first?
            .windows
            .filter({$0.isKeyWindow})
            .first
    }
}


extension UIView {
    func allSubviews() -> [UIView] {
        var subs = self.subviews
        for subview in self.subviews {
            let rec = subview.allSubviews()
            subs.append(contentsOf: rec)
        }
        return subs
    }
}
    

struct TabBarModifier {
    static func showTabBar() {
        UIApplication.shared.key?.allSubviews().forEach({ subView in
            if let view = subView as? UITabBar {
                view.isHidden = false
            }
        })
    }
    
    static func hideTabBar() {
        UIApplication.shared.key?.allSubviews().forEach({ subView in
            if let view = subView as? UITabBar {
                view.isHidden = true
            }
        })
    }
}

struct ShowTabBar: ViewModifier {
    func body(content: Content) -> some View {
        return content.padding(.zero).onAppear {
            TabBarModifier.showTabBar()
        }
    }
}
struct HiddenTabBar: ViewModifier {
    func body(content: Content) -> some View {
        return content.padding(.zero).onAppear {
            TabBarModifier.hideTabBar()
        }
    }
}

extension View {
    
    func showTabBar() -> some View {
        return self.modifier(ShowTabBar())
    }

    func hiddenTabBar() -> some View {
        return self.modifier(HiddenTabBar())
    }
}

extension View {
    func phoneOnlyStackNavigationView() -> some View {
        if UIDevice.current.userInterfaceIdiom == .phone {
            return AnyView(self.navigationViewStyle(StackNavigationViewStyle()))
        } else {
            return AnyView(self)
        }
    }
}
