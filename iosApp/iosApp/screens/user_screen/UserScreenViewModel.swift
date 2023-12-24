//
//  UserScreenViewModel.swift
//  iosApp
//
//  Created by MacBook on 24.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

class UserScreenViewModel:ObservableObject{
    
    @Published var user:User? = nil
    @Published var selectedLanguage = "English"
    @Published var nameChangeMode = false
    @Published var nameTextField = ""
    @Published var image = UIImage() {
        didSet{
            let byteArray =  self.image.jpegData(compressionQuality: 1.0)!
            UseCases().useUploadUserPhoto().execute(userId: user?.login ?? "", file: KotlinByteArray.from(data: byteArray), completionHandler: {res, err in
                self.userImageId = UUID()
            })
        }
    }
    @Published var showSheet = false
    @Published var userImageId = UUID()
    @Published var hasBeenExit = false

    
    let languages = ["Russian", "English"]
    
    init() {
        fetchUser()
    }
    
    func convertImageToBytesString(image: UIImage) -> String? {
        // Convert UIImage to Data
        if let imageData = image.jpegData(compressionQuality: 1.0) {
            // Convert Data to byte array
            let byteArray = [UInt8](imageData)

            // Convert byte array to string
            let byteString = byteArray.map { String(format: "%02X", $0) }.joined()

            return byteString
        }

        return nil
    }
    
    func switchNameChangeMode(){
        self.nameChangeMode = !self.nameChangeMode
        if(!nameChangeMode){
            user?.name = nameTextField
            UseCases().useEditUser().execute(user: self.user ?? User(name: "", number: "", login: "", password: ""), completionHandler: {res, err in
            })
            self.nameTextField = ""
        }
    }
    
    func fetchUser(){
        
        UseCases().useGetUserData().execute(completionHandler: {res, err in
            let user = res?.data
            self.user = user
        })
        
    }
    
    func changeUserPhoto(mode:Bool){
        self.showSheet = mode
    }
    
    func toExit(){
        UseCases().useDeleteUser().execute(completionHandler: {err in
            self.hasBeenExit = true
        })
    }
    
    
}


extension KotlinByteArray {
    static func from(data: Data) -> KotlinByteArray {
        let swiftByteArray = [UInt8](data)
        return swiftByteArray
            .map(Int8.init(bitPattern:))
            .enumerated()
            .reduce(into: KotlinByteArray(size: Int32(swiftByteArray.count))) { result, row in
                result.set(index: Int32(row.offset), value: row.element)
            }
    }
}
