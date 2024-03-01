//
//  PreferenciasView.swift
//  GestionAhorrosSwift
//
//  Created by Joel on 29/02/24.
//

import SwiftUI

struct PreferenciasView: View {
    var body: some View {
        List {
            Section("Maestros") {
                NavigationLink {
                    EmptyView()
                } label: {
                    Text("Cuentas")
                }
                NavigationLink {
                    EmptyView()
                        .navigationTitle("Categorias")
                } label: {
                    Image(systemName: "person")
                    Text("Categorias")
                }
            }
            
            Section("Cuenta") {
                Button {
                    
                } label: {
                    Text("Cerrar Sesion")
                }
            }
        }
        .navigationTitle("Preferencias")
        
    }
}

#Preview {
    PreferenciasView()
}
