//
//  FavoriteCoffeeCard.swift
//  iosApp
//
//  Created by MacBook on 20.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FavoriteCoffeeCard: View {
    
    let coffee:IdentifiableCoffee
    let height:Float
    let callback:() -> Void
    
    var body: some View {
        Button(action: {
            callback()
        }, label: {
            ZStack{
                Color.white
                HStack{
                    HStack{
                        AsyncImage(url: URL(string: Constants().COFFEE_PHOTOS_URL + coffee.id)){image in
                            image
                                .resizable()
                                
                        }placeholder: {
                            ZStack(alignment: .center){
                                ProgressView()
                            }
                        }
                        .frame(maxHeight: .infinity)
                        .frame(width: 128)
                        .clipShape(RoundedRectangle(cornerRadius: 16.0))
                        .padding(.leading, 8)
                        VStack(alignment: .leading){
                            Text(coffee.categoryTitle)
                                .font(.system(size: 16, weight: .semibold))
                                .foregroundColor(Color(hexStringToUIColor(hex: "2f2d2c")))
                            Text(coffee.subtitle)
                                .font(.system(size: 13, weight: .regular))
                                .foregroundColor(Color(hexStringToUIColor(hex: "9B9B9B")))
                            Spacer()
                            HStack{
                                Image(systemName: "heart.fill")
                                    .foregroundColor(Color.red)
                                Text(String(format: "$ %.2f", coffee.price))
                                    .font(.system(size: 12, weight: .regular))
                                    .foregroundColor(Color(hexStringToUIColor(hex: "2f4b4e")))
                                
                            }
                        }
                    }
                    .padding(8)
                    Spacer()
                    VStack{
                        ZStack{
                            Color.black.opacity(0.32)
                            HStack{
                                Image(systemName: "star.fill")
                                    .resizable()
                                    .foregroundColor(.yellow)
                                    .frame(maxWidth: 10, maxHeight: 10)
                                
                                Text(String(coffee.totalScore))
                                    .font(.system(size: 12, weight:.regular))
                                    .foregroundColor(.white)
                                    .padding(.leading, 2)
                                    .padding(.vertical, 6)

                            }
                        }
                        .frame(maxWidth: 55)
                        .frame(height: 25)
                        .cornerRadius(16, corners: [.bottomLeft, .topRight])
                        Spacer()
                        Button(action: {}, label: {
                            ZStack{
                                Color(hexStringToUIColor(hex: "C67C4E"))
                                Image(systemName: "plus")
                                    .foregroundColor(.white)
                            }
                        })
                        .frame(width: 32, height: 32)
                        .cornerRadius(10)
                    }
                    .padding(.bottom, 8)
                    
                }
            }
            .frame(maxHeight: CGFloat(self.height))
            .cornerRadius(16)
        })
    }
}

