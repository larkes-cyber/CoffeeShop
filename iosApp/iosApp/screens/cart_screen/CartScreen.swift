//
//  CartScreen.swift
//  iosApp
//
//  Created by MacBook on 19.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CartScreen: View {
    var body: some View {
        VStack{
            NavigationLink(destination:EmptyView(), label: {
             
            })
            .navigationTitle("Your Order")
            
            ZStack{
                Color.white
                ScrollView(.vertical){
                    VStack{
                        
                    }
                }
            }
        }
    }
}

#Preview {
    CartScreen()
}
