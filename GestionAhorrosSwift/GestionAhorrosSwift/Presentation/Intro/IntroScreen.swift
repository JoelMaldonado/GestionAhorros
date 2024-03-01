//
//  IntroScreen.swift
//  GestionAhorrosSwift
//
//  Created by Joel on 29/02/24.
//

import SwiftUI

struct IntroScreen: View {
    @AppStorage("isFirstTime") private var isFirstTime: Bool = true
    var body: some View {
        VStack(spacing: 15) {
            
            Text("What's New in the\nExpense Tracker")
                .font(.largeTitle.bold())
                .multilineTextAlignment(.center)
                .padding(.top, 65)
                .padding(.bottom, 35)
            VStack(alignment: .leading, spacing: 25) {
                PointView(symbol: "dollarsign", title: "Transactions", subtitle: "Keep track of your earning and expenses.")
                PointView(symbol: "chart.bar.fill", title: "Visual Charts", subtitle: "View your transactions using eye-catching graphic representations.")
                PointView(symbol: "magnifyingglass", title: "Advance Filters", subtitle: "Find the expenses you want by advance search and filtering")
            }
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(.horizontal, 25)
            
            Spacer()
            
            Button {
                isFirstTime = false
            } label: {
                Text("Continue")
                    .fontWeight(.bold)
                    .foregroundStyle(.white)
                    .frame(maxWidth: .infinity)
                    .padding(.vertical, 14)
                    .background(appTint.gradient)
                    .clipShape(.buttonBorder)
            }
        }
        .padding()
    }
    
    @ViewBuilder
    func PointView(symbol: String, title: String, subtitle: String) -> some View {
        HStack(spacing: 15) {
            Image(systemName: symbol)
                .font(.largeTitle)
                .foregroundStyle(appTint.gradient)
                .frame(width: 45)
            
            VStack(alignment: .leading) {
                Text(title)
                    .font(.title3)
                    .fontWeight(.semibold)
                Text(subtitle)
                    .foregroundStyle(.gray)
            }
        }
    }
}

#Preview {
    IntroScreen()
}
