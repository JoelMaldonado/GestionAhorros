//
//  MenuView.swift
//  GestionAhorrosSwift
//
//  Created by Joel on 23/02/24.
//

import SwiftUI

struct MenuView: View {
    @AppStorage("isFirstTime") private var isFirstTime: Bool = true
    
    @State private var activeTab: Tab = .inicio
    var body: some View {
        TabView(selection: $activeTab) {
            
            HomeView()
                .tag(Tab.inicio)
                .tabItem { Tab.inicio.tabContent }
            
            Text("Buscar")
                .tag(Tab.buscar)
                .tabItem { Tab.buscar.tabContent }
            PreferenciasView()
                .tag(Tab.preferencias)
                .tabItem { Tab.preferencias.tabContent }
        }
        .tint(appTint)
        .sheet(isPresented: $isFirstTime, content: {
            IntroScreen()
                .interactiveDismissDisabled()
        })
    }
}
