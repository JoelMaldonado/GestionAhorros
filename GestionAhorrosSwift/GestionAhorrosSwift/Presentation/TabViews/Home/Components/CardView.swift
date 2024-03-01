//
//  CardView.swift
//  GestionAhorrosSwift
//
//  Created by Joel on 29/02/24.
//

import SwiftUI

struct CardView: View {
    var income:Double
    var expense: Double
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 15)
                .fill(.background)
            
            VStack(spacing: 0) {
                HStack(spacing: 12) {
                    Text("\(currencyString(income - expense))")
                        .font(.title.bold())
                    
                    Image(systemName: expense > income ? "chart.line.downtrend.xyaxis" : "chart.line.uptrend.xyaxis")
                        .font(.title3)
                        .foregroundStyle(expense > income ? .red : .green)
                }
                .padding(.bottom, 25)
                
                HStack(spacing: 0) {
                    ForEach(Category.allCases, id: \.rawValue) { cat in
                        let symbolImage = cat == .income ? "arrow.down" : "arrow.up"
                        let tint : Color = cat == .income ? .green : .red
                        HStack(spacing: 10) {
                            Image(systemName: symbolImage)
                                .font(.callout.bold())
                                .foregroundStyle(tint)
                                .frame(width: 35, height: 35)
                                .background {
                                    Circle()
                                        .fill(tint.opacity(0.25).gradient)
                                }
                            VStack(alignment: .leading, spacing: 4) {
                                Text(cat == .income ? "Ingreso" : "Gastos")
                                    .font(.caption2)
                                    .foregroundStyle(.gray)
                                Text(currencyString(cat == .income ? income : expense, allowedDigits: 0))
                                    .font(.callout)
                                    .fontWeight(.semibold)
                                    .foregroundStyle(.primary)
                            }
                            
                            if cat == .income {
                                Spacer()
                            }
                        }
                    }
                }
            }
            .padding([.horizontal, .bottom], 25)
            .padding(.top, 15)
        }
    }
}

#Preview {
    ScrollView {
        CardView(income: 4590.7673, expense: 2389.29332)
    }
}
