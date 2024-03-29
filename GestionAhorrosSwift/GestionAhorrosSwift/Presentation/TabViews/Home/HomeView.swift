//
//  HomeView.swift
//  GestionAhorrosSwift
//
//  Created by Joel on 29/02/24.
//

import SwiftUI

struct HomeView: View {
    @AppStorage("userName") private var userName: String = ""
    
    @State private var startDate: Date = .now.startOfMonth
    @State private var endDate: Date = .now.endOfMonth
    
    @State private var showFilterView: Bool = false
    
    @State private var selectedCategory: Category = .expense
    
    @Namespace private var animation
    var body: some View {
        GeometryReader {
            let size = $0.size
            
            NavigationStack {
                ScrollView(.vertical) {
                    LazyVStack(spacing: 10, pinnedViews: [.sectionHeaders]) {
                        Section {
                            Button {
                                showFilterView = true
                            } label: {
                                Text("\(format(date: startDate, format: "dd - MMM yy")) to \(format(date: endDate,format: "dd - MMM yy"))")
                                    .font(.caption2)
                                    .foregroundStyle(.gray)
                            }
                            .hSpacing(.leading)
                            
                            CardView(income: 2039, expense: 4098)
                            
                            CustomSegmentedControl()
                                .padding(.bottom, 10)
                            
                            ForEach(sampleTransactions.filter({ $0.category == selectedCategory.rawValue }), id: \.self.id) { item in
                                CardTransaction(transaction: item)
                            }
                        } header: {
                            HeaderView(size)
                        }
                    }
                    .padding(15)
                }
                .background(.gray.opacity(0.15))
                .blur(radius: showFilterView ? 5 : 0)
                .disabled(showFilterView)
            }
            .overlay {
                
                if showFilterView {
                    DateFilterView(
                        start: startDate,
                        end: endDate,
                        onSubmit: { start, end in
                            startDate = start
                            endDate = end
                            showFilterView = false
                        },
                        onClose: {
                            showFilterView = false
                        }
                    )
                    .transition(.move(edge: .leading))
                }
            }
            .animation(.snappy, value: showFilterView)
            
        }
    }
    
    func headerBGOpacity(_ proxy: GeometryProxy) -> CGFloat {
        let minY = proxy.frame(in: .scrollView).minY + safeArea.top
        return minY > 0 ? 0 : (-minY / 15)
    }
    
    func headerScale(_ size: CGSize, proxy: GeometryProxy) -> CGFloat {
        let minY = proxy.frame(in: .scrollView).minY
        let screenHeight = size.height
        
        let progress = minY / screenHeight
        let scale = (min(max(progress, 0), 1)) * 0.4
        return 1 + scale
    }
}

extension HomeView {
    
    @ViewBuilder
    func HeaderView(_ size: CGSize) -> some View {
        HStack {
            VStack(alignment: .leading, spacing: 5) {
                Text("Bienvenido!")
                    .font(.title)
                    .bold()
                
                if !userName.isEmpty {
                    Text(userName)
                        .font(.callout)
                        .foregroundStyle(.gray)
                }
            }
            .visualEffect { content, geometryProxy in
                content
                    .scaleEffect(headerScale(size, proxy: geometryProxy), anchor: .topLeading)
            }
            
            Spacer()
            
            NavigationLink {
                
            } label: {
                Image(systemName: "plus")
                    .font(.title3)
                    .fontWeight(.semibold)
                    .foregroundStyle(.white)
                    .frame(width: 45, height: 45)
                    .background(appTint.gradient)
                    .clipShape(.circle)
            }
        }
        .padding(.bottom, userName.isEmpty ? 10 : 5)
        .background {
            VStack(spacing: 0){
                
                Rectangle()
                    .fill(.ultraThinMaterial)
                
                Divider()
            }
            .visualEffect { content, geometryProxy in
                content
                    .opacity(headerBGOpacity(geometryProxy))
            }
            .padding(.horizontal, -15)
            .padding(.top, -(safeArea.top + 15))
        }
    }
    
    @ViewBuilder
    func CustomSegmentedControl() -> some View {
        HStack(spacing: 0) {
            ForEach(Category.allCases, id: \.rawValue) { cat in
                Text(cat == .income ? "Ingreso" : "Gastos")
                    .hSpacing()
                    .padding(.vertical, 10)
                    .background {
                        if cat == selectedCategory {
                            Capsule()
                                .fill(.background)
                                .matchedGeometryEffect(id: "ACTIVETAB", in: animation)
                        }
                    }
                    .contentShape(.capsule)
                    .onTapGesture {
                        withAnimation(.snappy) {
                            self.selectedCategory = cat
                        }
                    }
            }
        }
        .background(.gray.opacity(0.15))
        .clipShape(.capsule)
        .padding(.top, 5)
    }
    
}

struct DateFilterView: View {
    @State var start: Date
    @State var end: Date
    var onSubmit: (Date, Date) -> ()
    var onClose: () -> ()
    var body: some View {
        VStack(spacing: 15) {
            DatePicker("Fecha Inicio", selection: $start, displayedComponents: [.date])
            DatePicker("Fecha Fin", selection: $start, displayedComponents: [.date])
            HStack(spacing: 15) {
                Button("Cancelar") {
                    onClose()
                }
                .buttonStyle(.borderedProminent)
                .buttonBorderShape(.roundedRectangle(radius: 5))
                .tint(.red)
                
                Button("Filtrar") {
                    onSubmit(start, end)
                }
                .buttonStyle(.borderedProminent)
                .buttonBorderShape(.roundedRectangle(radius: 5))
                .tint(appTint)
            }
            .padding(.top, 10)
        }
        .padding(15)
        .background(.bar, in: .rect(cornerRadius: 10))
        .padding(.horizontal, 30)
    }
}


#Preview {
    HomeView()
}
