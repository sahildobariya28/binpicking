import UIKit
import SwiftUI
import shared
import Network

class NetworkMonitor: ObservableObject {
    private let monitor = NWPathMonitor()
    private let queue = DispatchQueue (label: "Monitor")
    @Published var isActive = true
    @Published var isExpensive = true
    @Published var isConstrained = true
    @Published var connectionType = NWInterface.InterfaceType.other

    init() {
        monitor.pathUpdateHandler = { path in
            DispatchQueue.main.async {
                self.isActive = path.status == .satisfied
                self.isExpensive = path.isExpensive
                self.isConstrained = path.isConstrained

                let connectionTypes: [NWInterface.InterfaceType] = [.cellular, .wifi, .wiredEthernet]
                self.connectionType = connectionTypes.first(where: path.usesInterfaceType) ?? .other
            }
        }
        monitor.start(queue: queue)
    }
}

struct ComposeView: UIViewControllerRepresentable {
    @StateObject var networkMonitor = NetworkMonitor()

    func makeUIViewController(context: Context) -> UIViewController {

        let kmmObject = NSObject()
        let pref = KMMPreference(context: kmmObject)
        return MainViewControllerKt.MainViewController(browserWrapper: BrowserWrapper(), context: pref)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {

    @StateObject var networkMonitor = NetworkMonitor()
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
                .alert(isPresented: .constant(!networkMonitor.isActive)) {
                    Alert(
                        title: Text("No Internet"),
                        message: Text("Please check your internet connection and try again."),
                        dismissButton: .default(Text("OK"))
                    )
                }
    }
}
