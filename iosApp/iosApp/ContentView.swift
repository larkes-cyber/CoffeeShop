import SwiftUI
import shared

struct ContentView: View {

    @ObservedObject private var viewModel = ViewModel()
    
	var body: some View {
        Button(action: {
            viewModel.authUser(login: "horse@gmail.com", passcode: "12345678")
        }, label: {
            Text("Click")
        })
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
