//
//  CoffeeCartCard.swift
//  iosApp
//
//  Created by MacBook on 20.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CoffeeCartCard: View {
    
    let coffee:Coffee?
    var amount:Int
    let amountHasChagedCallback:(Int) -> Void
    
    var body: some View {
        HStack{
            HStack{
                AsyncImage(url: URL(string: Constants().COFFEE_PHOTOS_URL + (coffee?.id ?? ""))){image in
                    image
                        .resizable()
                        
                }placeholder: {
                    ZStack(alignment: .center){
                        ProgressView()
                    }
                }
                .cornerRadius(12)
                .frame(width: 54, height: 54)
                .padding(.trailing, 12)
                
                VStack(alignment:.leading){
                    Text(coffee?.categoryTitle ?? "")
                        .font(.system(size: 18, weight: .semibold))
                        .foregroundColor(Color(hexStringToUIColor(hex: "2F2D2C")))
                        .padding(.bottom, 4)
                    Text(coffee?.subtitle ?? "")
                        .font(.system(size: 16, weight: .regular))
                        .foregroundColor(Color(hexStringToUIColor(hex: "9B9B9B")))
                }
            }
            Spacer()
            HStack{
                SmallIncBtn(systemIcon: "minus"){
                    amountHasChagedCallback(-1)
                }
                Text(String(amount))
                    .font(.system(size: 16, weight: .semibold))
                    .padding(.horizontal, 14)
                SmallIncBtn(systemIcon: "plus"){
                    amountHasChagedCallback(1)
                }
            }
        }
    }
}



struct SmallIncBtn:View {
    
    let systemIcon:String
    let callback:() -> Void
    
    var body: some View {
        Button(action: {
            callback()
        }, label: {
            Image(systemName: systemIcon)
                .foregroundColor(.black)
                .frame(width: 28, height: 28)
        })
        .overlay(
            RoundedRectangle(cornerRadius: 100)
                .stroke(Color(hexStringToUIColor(hex: "EAEAEA")), lineWidth: 1)
        )
        
    }
}
