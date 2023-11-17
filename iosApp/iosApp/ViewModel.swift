//
//  ViewModel.swift
//  iosApp
//
//  Created by MacBook on 17.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared


class ViewModel:ObservableObject{

    func authUser(login:String, passcode:String){
        UseCases().useAuthUser().execute(login: login, password: passcode, completionHandler: { (res, err) in
            print(String(res?.data ?? ""))
        })
    }

}

