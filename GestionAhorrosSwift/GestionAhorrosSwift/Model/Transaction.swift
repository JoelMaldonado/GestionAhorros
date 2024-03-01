//
//  Transaction.swift
//  GestionAhorrosSwift
//
//  Created by Joel on 29/02/24.
//

import SwiftUI

struct Transaction: Identifiable {
    var id: UUID = .init()
    
    var title: String
    var remarks: String
    var amount: Double
    var dateAded: Date
    var category: String
    var tintColor: String
    
    init(title: String, remarks: String, amount: Double, dateAded: Date, category: Category, tintColor: TintColor) {
        self.title = title
        self.remarks = remarks
        self.amount = amount
        self.dateAded = dateAded
        self.category = category.rawValue
        self.tintColor = tintColor.color
    }
    
    var color: Color {
        return tints.first(where: { $0.color == tintColor })?.value ?? appTint
    }
}


var sampleTransactions: [Transaction] = [
    .init(title: "Iphone 15 Pro Max", remarks: "Compra", amount: 5199, dateAded: .now, category: .expense, tintColor: tints.randomElement()!),
    .init(title: "Pollito con papas", remarks: "Comida", amount: 12.99, dateAded: .now, category: .expense, tintColor: tints.randomElement()!),
    .init(title: "Chifita", remarks: "Comida", amount: 15, dateAded: .now, category: .expense, tintColor: tints.randomElement()!),
    .init(title: "Pasaje Taxi", remarks: "Transporte", amount: 4.8, dateAded: .now, category: .income, tintColor: tints.randomElement()!),
]
