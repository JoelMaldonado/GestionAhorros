//
//  Tab.swift
//  GestionAhorrosSwift
//
//  Created by Joel on 29/02/24.
//

import SwiftUI

enum Tab: String {
    case inicio = "Inicio"
    case buscar = "Buscar"
    case preferencias = "Preferencias"
    
    @ViewBuilder
    var tabContent: some View {
        switch self {
        case .inicio:
            Image(systemName: "house")
            Text(self.rawValue)
        case .buscar:
            Image(systemName: "magnifyingglass")
            Text(self.rawValue)
        case .preferencias:
            Image(systemName: "gearshape")
            Text(self.rawValue)
        }
    }
}
