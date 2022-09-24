(ns aula3.exercicio
  (:require [aula3.data :as data]))

;; escrever uma funcao que retorna a primeira compra do cliente dado o ID.
(defn retorna-primeira-compra
  [id mapa]
  (let [{compras :compras} mapa]
    (first (get compras id))))

(defn first-purchase
  [id mapa]
  (let [{{[primeira-compra-cliente] id}
         :compras} mapa]
  primeira-compra-cliente))

;; escrever uma funcao que retorna as compras de um cliente dado o ID.
(defn retorna-compras-por-id
  [id mapa]
  (let [{compras :compras} mapa]
    (get compras id)))

;; escrever uma funcao que adiciona a compra {:item "mesa" :valor 250} no vetor de compras de um cliente, dado o ID.
;; Deve retornar o mapa inteiro.
;; com assoc-in
(defn adiciona-compra
  [{compras :compras :as mapa} id nova-compra]
  (let [{compras-cliente id} compras]
    (assoc-in mapa [:compras id]
              (conj compras-cliente nova-compra))))

;; com update-in
(defn adiciona-nova-compra-por-id
  [id mapa nova-compra]
  (update-in mapa [:compras id] conj nova-compra))

;; escrever uma funcao que da desconto de 10% para o primeiro item de compra do cliente dado o ID.
;; Deve retornar o mapa inteiro
;; com assoc-in
(defn adiciona-desconto
  [{compras :compras :as mapa} id]
  (let [{[{valor-primeira-compra :valor}] id} compras]
    (assoc-in mapa [:compras id 0 :valor] (* 0.9 valor-primeira-compra))))

;; com update-in
(defn aplica-desconto-primeira-compra
  [id mapa]
  (update-in mapa [:compras id 0 :valor] #(* % 0.9)))

(comment
  (retorna-primeira-compra "1" data/mapa)
  (retorna-compras-por-id "1" data/mapa)
  (adiciona-nova-compra-por-id "1" data/mapa {:item "mesa" :valor 250})
  (aplica-desconto-primeira-compra "1" data/mapa)
