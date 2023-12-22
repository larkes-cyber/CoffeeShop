//
//  PaymentRow.swift
//  iosApp
//
//  Created by MacBook on 22.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PaymentRow: View {
    
    let title:String
    let price:Float
    
    var body: some View {
        HStack{
            Text(self.title)
                .font(.system(size: 16, weight: .regular))
            Spacer()
            Text(String(format: "$ %.2f", self.price))
                .font(.system(size: 16, weight: .semibold))
        }
    }
}


