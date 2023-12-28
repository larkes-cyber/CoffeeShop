//
//  OrdersScreen.swift
//  iosApp
//
//  Created by MacBook on 19.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct UserScreen: View {
    
    @StateObject private var viewModel = UserScreenViewModel()
    
    var body: some View {
        
        ZStack{
            NavigationLink(destination: SplashScreen(), isActive: $viewModel.hasBeenExit, label: {
                EmptyView()
            })
            .navigationBarHidden(true)
            Color(hexStringToUIColor(hex: "F9F9F9"))
            ScrollView(.vertical){
                VStack{
                    ZStack{
                        LinearGradient(gradient: Gradient(colors: [Color(hexStringToUIColor(hex: "131313")), Color(hexStringToUIColor(hex: "313131"))]), startPoint: .leading, endPoint: .trailing)
                        VStack{
                            Button(action: {
                                viewModel.changeUserPhoto(mode: true)
                            }, label: {
                                ZStack(alignment: .bottomTrailing){
                                    AsyncImage(url: URL(string: Constants().USER_PHOTO_URL + (viewModel.user?.login ?? ""))){image in
                                        image
                                            .resizable()
                                            .cornerRadius(360)
                                    }placeholder: {
                                        ZStack(alignment: .center){
                                            Image(systemName: "person.circle")
                                                .resizable()
                                                .frame(width: 89, height: 89)
                                                .foregroundColor(.white)
                                          
                                        }
                                    }
                                    .id(viewModel.userImageId)
                                
                                    Image(systemName: "plus.circle")
                                        .resizable()
                                        .foregroundColor(Color(hexStringToUIColor(hex: "F2F2F2")))
                                        .frame(width: 25, height: 25)
                                }
                                .frame(width: 89, height: 89)
                                .padding(.top, 65)
                            })
                            HStack{
                                if(viewModel.nameChangeMode){
                                    TextField(viewModel.user?.name ?? "", text: $viewModel.nameTextField)
                                        .font(.system(size: 20, weight: .bold))
                                        .foregroundColor(.white)
                                        .frame(width: 130)
                                }else{
                                    Text(viewModel.user?.name ?? "")
                                        .font(.system(size: 20, weight: .bold))
                                        .foregroundColor(.white)
                                        .padding(.trailing, 5)
                                }
                                Button(action: {
                                    viewModel.switchNameChangeMode()
                                }, label: {
                                    Image(systemName: viewModel.nameChangeMode ? "checkmark" : "pencil")
                                        .foregroundColor(.white)
                                })
                            }
                            .padding(.bottom, 47)
                            .padding(.top, 20)
                            
                        }
                    }
                    
                    VStack(alignment: .leading, spacing: 17){
                        Text("Settings")
                            .font(.system(size: 20, weight: .semibold))
                            .foregroundColor(.black)
                        HStack{
                            HStack{
                                Image(systemName: "phone.down.fill")
                                    .foregroundColor(.black)
                                Text(viewModel.user?.number ?? "+79XXXXXXXXX")
                                    .padding(.leading, 14)
                            }
                            Spacer()
                            Button(action: {}, label: {
                                Image(systemName: "pencil")
                                    .foregroundColor(.black)
                            })
                        }
                        HStack{
                            Text("Select your language")
                            Spacer()
                            Picker("English", selection: $viewModel.selectedLanguage) {
                                ForEach(viewModel.languages, id: \.self) {
                                    Text($0)
                                        .foregroundColor(.black)
                                }
                            }
                            .accentColor(.black)
                        }
                        Button(action: {
                            viewModel.toExit()
                        }, label: {
                            Text("Exit")
                                .foregroundColor(.red)
                        })
                    }
                    .padding(.top, 15)
                    .padding(.horizontal, 27)
                    .padding(.bottom, 70)
                }
            }
        }
        .ignoresSafeArea()
        .sheet(isPresented: $viewModel.showSheet) {
            ImagePicker(sourceType: .photoLibrary, selectedImage: $viewModel.image)
        }
        .refreshable {
            viewModel.syncData()
        }
    }
}

#Preview {
    UserScreen()
}
