//
//  PasswordTextField.swift
//  iosApp
//
//  Created by MacBook on 11.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI


struct PasswordTextField: View {
    
    @Binding var text:String
    var placeholder:String
    
    var body: some View {
        ZStack{
            if(text.isEmpty){
                Text(placeholder)
                    .foregroundColor(Color(hexStringToUIColor(hex: "E6E0E9")))
                    .frame(maxWidth:.infinity, alignment: .leading)
                    .font(.system(size: 16, weight:.regular))
                    .padding(.leading, 16)
            }
            SecureField("",text: $text)
                .textFieldStyle(OvalTextFieldStyle())
                .overlay(
                    RoundedRectangle(cornerRadius: 4)
                        .stroke(Color(hexStringToUIColor(hex: "E6E0E9")), lineWidth: 1)
                )
        }
    }
}

