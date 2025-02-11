import SwiftUI
import shared
import FirebaseCore

class AppDelegate: NSObject, UIApplicationDelegate {
  func application(_ application: UIApplication,
                   didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
    FirebaseApp.configure()

    return true
  }
}


@main
struct iOSApp: App {
  // register app delegate for Firebase setup
  @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate


  var body: some Scene {
      WindowGroup {
                  ZStack {
                      
                      Color(hex: 0x07253B)
                          .edgesIgnoringSafeArea(.top) // Cover the status bar area

                      ContentView() // Your main content view
                  }
                  .environment(\.colorScheme, .light) // Force light mode for consistent UI
              }
  }
}

class HostingController<Content: View>: UIHostingController<Content> {
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .darkContent // This ensures black text/icons in the status bar
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        overrideUserInterfaceStyle = .light // Ensure light appearance for status bar elements
    }
}


//@main
//struct iOSApp: App {
//    FirebaseApp.configure()
//    var body: some Scene {
//        WindowGroup {
//            ContentView()
//        }
//    }
//}

extension Color {
    init(hex: UInt, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
}
