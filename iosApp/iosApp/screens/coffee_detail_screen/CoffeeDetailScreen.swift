//
//  CoffeeDetailScreen.swift
//  iosApp
//
//  Created by MacBook on 19.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CoffeeDetailScreen: View {
    
    private let id:String
    @StateObject private var viewModel = CoffeeDetailViewModel()
    
    init(id: String) {
        self.id = id
    }
    
    var body: some View {
        
        NavigationLink(destination:EmptyView(), label: {
         
        })
        .navigationTitle("Detail")
        .toolbar{
            Button(action: {}, label: {
                Image(systemName: "suit.heart")
            })
        }
        
        ZStack{
            Color.white
            ScrollView(.vertical){
                VStack(alignment: .leading){
                    AsyncImage(url: URL(string: Constants().COFFEE_PHOTOS_URL + self.id)){image in
                        image
                            .resizable()
                            
                    }placeholder: {
                        ProgressView()
                    }
                    .frame(height: 226)
                    .padding(.bottom, 18)
                    VStack(alignment:.leading){
                        Text(viewModel.coffee?.categoryTitle ?? "")
                            .font(.system(size: 22, weight:.semibold))
                            .foregroundColor(Color(hexStringToUIColor(hex: "2f2d2c")))
                            .padding(.bottom, 8)
                            .frame(alignment: .leading)
                        
                        Text(viewModel.coffee?.subtitle ?? "")
                            .font(.system(size: 14, weight:.regular))
                            .foregroundColor(Color(hexStringToUIColor(hex: "9B9B9B")))
                            .frame(alignment: .leading)
                    }
                    HStack(alignment: .center){
                        HStack(alignment:.center){
                            Image(systemName: "star.fill")
                                .foregroundColor(.yellow)
                            Text(String(viewModel.coffee?.totalScore ?? 0))
                                .font(.system(size: 18, weight: .semibold))
                                .foregroundColor(Color(hexStringToUIColor(hex: "2f2d2c")))
                            Text("(\(viewModel.coffee?.scoreCount ?? 0))")
                                .font(.system(size: 14, weight: .regular))
                                .foregroundColor(Color(hexStringToUIColor(hex: "808080")))
                        }
                        Spacer()
                        HStack{
                            ZStack{
                                Color(hexStringToUIColor(hex: "F9F9F9"))
                                Image("bean")
                            }
                            .frame(width: 44, height: 44)
                            .cornerRadius(14)
                            ZStack{
                                Color(hexStringToUIColor(hex: "F9F9F9"))
                                Image("milk")
                            }
                            .frame(width: 44, height: 44)
                            .cornerRadius(14)
                        }
                    }
                    
                    Divider()
                        .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: 1)
                        .foregroundColor(Color(hexStringToUIColor(hex: "EAEAEA")))
                        .padding(.bottom, 20)
                    
                    Text("Description")
                        .font(.system(size: 18, weight:.semibold))
                        .foregroundColor(Color(hexStringToUIColor(hex: "2f2d2c")))
                        .padding(.bottom, 8)
                        .frame(alignment: .leading)
                    
                    Text(viewModel.coffee?.description_ ?? "")
                        .font(.system(size: 16, weight:.semibold))
                        .foregroundColor(Color(hexStringToUIColor(hex: "9B9B9B")))
                        .padding(.bottom, 8)
                        .frame(alignment: .leading)

                    Text("Size")
                        .font(.system(size: 18, weight:.semibold))
                        .foregroundColor(Color(hexStringToUIColor(hex: "2f2d2c")))
                        .padding(.bottom, 8)
                        .frame(alignment: .leading)
                    
                    
                    
                }
                .padding(.top, 25)
                .padding(.horizontal, 40)
                .frame(maxWidth: .infinity)
            }
        }
        .hiddenTabBar()
        .onAppear(perform: {
            viewModel.getCoffee(id: id)
        })
    }
}


