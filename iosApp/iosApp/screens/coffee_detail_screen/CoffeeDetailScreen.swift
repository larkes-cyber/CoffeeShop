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
            Button(action: {
                viewModel.switchFavoriteCoffee()
            }, label: {
                Image(systemName: viewModel.isFavorite ? "suit.heart.fill" : "suit.heart")
            })
        }
        
        ZStack(alignment: .bottom){
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
                    .padding(.top, 82)
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
                        .padding(.bottom, 11)
                        .frame(alignment: .leading)

                    Text("Size")
                        .font(.system(size: 18, weight:.semibold))
                        .foregroundColor(Color(hexStringToUIColor(hex: "2f2d2c")))
                        .padding(.bottom, 8)
                        .frame(alignment: .leading)
                    
                    HStack{
                        SmallTabButton(text: "S", isSelected: viewModel.selectedTitle == 0, callback: {
                            viewModel.selectTitle(index: 0)
                        })
                        SmallTabButton(text: "M", isSelected: viewModel.selectedTitle == 1, callback: {
                            viewModel.selectTitle(index: 1)
                        })
                        SmallTabButton(text: "L", isSelected: viewModel.selectedTitle == 2, callback: {
                            viewModel.selectTitle(index: 2)
                        })
                    }
                    .padding(.bottom, 14)
                    Spacer()
                        .frame(height: 120)
                }
                .padding(.horizontal, 40)
            }
            .padding(.top, 25)
            .frame(maxWidth: .infinity)
            
            ZStack{
                Color.white
                HStack{
                    VStack{
                        Text("Price")
                            .foregroundColor(Color(hexStringToUIColor(hex: "989898")))
                            .font(.system(size: 14, weight: .regular))
                            .padding(.bottom, 8)
                        Text(String(format: "$ %.2f", viewModel.coffee?.price ?? 0))
                            .foregroundColor(Color(hexStringToUIColor(hex: "C67C4E")))
                            .font(.system(size: 20, weight: .semibold))
                    }.padding(.trailing, 40)
                    
                    if(viewModel.hasBeenAddedToCart){
                        OutlineButtonWithCheck(title: "Added to cart"){
                            viewModel.switchCartBtnMode()
                        }
                    }else{
                        AppPrimaryButton(title: "Buy Now"){
                            viewModel.switchCartBtnMode()
                        }
                    }
                }
                .padding(.bottom, 20)
                .padding(.horizontal, 30)
            }
            .overlay(
                RoundedRectangle(cornerRadius: 24)
                    .stroke(Color(hexStringToUIColor(hex: "F1F1F1")), lineWidth: 1)
            )
            .frame(maxHeight: 114, alignment: .bottom)
    
        }
        .ignoresSafeArea()
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .hiddenTabBar()
        .onAppear(perform: {
            viewModel.getCoffee(id: id)
        })
    }
}


