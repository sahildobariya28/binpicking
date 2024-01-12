import UIKit
import SwiftUI
import shared

@main
struct AppDelegate: App{

   
    
   init() {
       InitKoinKt.doInitKoin()
   }

    var body: some Scene {
            WindowGroup {
                ContentView()
            }
        }
}

