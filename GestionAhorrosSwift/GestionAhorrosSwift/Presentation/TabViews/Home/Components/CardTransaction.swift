//
//  CardTransaction.swift
//  GestionAhorrosSwift
//
//  Created by Joel on 29/02/24.
//

import SwiftUI

struct CardTransaction: View {
    var transaction: Transaction
    var body: some View {
        HStack(spacing: 12) {
            Text("\(String(transaction.title.prefix(1)))")
                .font(.title)
                .fontWeight(.semibold)
                .foregroundStyle(.white)
                .frame(width: 45, height: 45)
                .background(transaction.color.gradient)
                .clipShape(.circle)
            
            VStack(alignment: .leading, spacing: 4) {
                Text(transaction.title)
                    .foregroundStyle(Color.primary)
                Text(transaction.remarks)
                    .font(.caption)
                    .foregroundStyle(Color.primary.secondary)
                Text(format(date: transaction.dateAded, format: "dd MMM yyyy"))
            }
            .lineLimit(1)
            .hSpacing(.leading)
            
            Text(currencyString(_: transaction.amount))
                .fontWeight(.semibold)
        }
        .padding(.horizontal, 15)
        .padding(.vertical, 10)
        .background(.background, in: .rect(cornerRadius: 16))
    }
}

#Preview {
    CardTransaction(transaction: sampleTransactions[0])
}
