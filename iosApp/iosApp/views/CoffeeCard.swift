//
//  CoffeeCard.swift
//  iosApp
//
//  Created by MacBook on 15.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CoffeeCard: View {
    
    @State var hasBeenAddedToCart = false
    @State var delay = false
    
    let coffee:IdentifiableCoffee
    let adddedToCartCallback:() -> Void
    let callback:() -> Void
    var timer = Timer()

    
    func delayAction(){
        DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
            self.delay = false
            self.hasBeenAddedToCart = !self.hasBeenAddedToCart
        }
    }
    
    var body: some View {
        Button(action: {
            callback()
        }, label: {
            ZStack(alignment: .topLeading){
                Color.white
                VStack(alignment: .leading){
                    AsyncImage(url: URL(string: Constants().COFFEE_PHOTOS_URL + coffee.id)){image in
                        image
                            .resizable()
                            
                    }placeholder: {
                        ZStack(alignment: .center){
                            ProgressView()
                        }
                    }
                    .frame(width: 141, height: 132)
                    .clipShape(RoundedRectangle(cornerRadius: 16.0))
          
                    Text(coffee.categoryTitle)
                        .font(.system(size: 16, weight:.semibold))
                        .foregroundColor(Color(hexStringToUIColor(hex: "2F2D2C")))
                        .padding(.top, 12)
                        .padding(.bottom, 4)

                    Text(coffee.subtitle)
                        .font(.system(size: 12, weight:.regular))
                        .foregroundColor(Color(hexStringToUIColor(hex: "9B9B9B")))
                        .padding(.bottom, 12)

                    
                    HStack{
                        Text(String(format: "$ %.2f", coffee.price))
                            .font(.system(size: 18, weight:.semibold))
                            .foregroundColor(Color(hexStringToUIColor(hex: "2F4B4E")))
                        Spacer()
                        SmallActionBtn(iconSystemName:self.hasBeenAddedToCart ? "checkmark" : "plus", callback: {
                            if(!self.delay){
                                withAnimation{
                                    self.delay = true
                                    self.hasBeenAddedToCart = !self.hasBeenAddedToCart
                                    self.adddedToCartCallback()
                                    self.delayAction()
                                }
                            }
                        })
                    }
                }
                .padding()
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
                .frame(height: 25, alignment: .topLeading)
                .cornerRadius(16, corners: [.topLeft, .bottomRight])
            }
            .cornerRadius(16, corners: .allCorners)
        })
    }
}


extension View {
    func cornerRadius(_ radius: CGFloat, corners: UIRectCorner) -> some View {
        clipShape( RoundedCorner(radius: radius, corners: corners) )
    }
}

struct RoundedCorner: Shape {

    var radius: CGFloat = .infinity
    var corners: UIRectCorner = .allCorners

    func path(in rect: CGRect) -> Path {
        let path = UIBezierPath(roundedRect: rect, byRoundingCorners: corners, cornerRadii: CGSize(width: radius, height: radius))
        return Path(path.cgPath)
    }
}

