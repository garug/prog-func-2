(ns aula9.exercicio
  (:require [aula9.dados-banco :as data]))

(defn acha-index
  [col regra]
  (loop [index 0]
    (let [el (get col index)]
      (cond
        (= el nil) nil
        (regra (get col index)) index
        :else (recur (inc index))))))

;; 1 crie um átomo com o vetor dados banco.
(def banco (atom data/banco-db))

;; 2 crie uma função capaz de trazer um cliente de acordo com o seu id
(defn busca-cliente-por-id
  [banco id]
  (first (filter #(= id (:id %)) @banco)))

;; 3 crie uma função que, dado um valor adiciona esse valor do saldo existente de um cliente dado id. A função deve printar o valor anterior do saldo
(defn adiciona-saldo-por-id
  [banco valor id]
  (if-let [index (acha-index @banco #(= id (:id %)))]
    (do
      (println (:saldo (get @banco index)))
      (swap! banco update-in [index :saldo] + valor))))

;; 4 crie uma função que, dado um valor subtrai esse valor do saldo existente de um cliente dado id. A função deve printar o valor anterior do saldo 
(defn remove-saldo-por-id
  [banco valor id]
  (if-let [index (acha-index @banco #(= id (:id %)))]
    (do
      (println (:saldo (get @banco index)))
      (swap! banco update-in [index :saldo] - valor))))

;; 5 crie uma função chamada 'transação' da qual você passa uma das operações (deposito/saque), o valor a ser depositado/retirado e efetua uma transação bancária.
(defn transacao
  [banco transacao id valor]
  (if-let [index (acha-index @banco #(= id (:id %)))]
    (swap! banco update-in [index :saldo] transacao valor)))

;; testes manuais
(comment
  (acha-index @banco #(= 70 (:id %)))
  (busca-cliente-por-id banco 70)
  (adiciona-saldo-por-id banco 8 8)
  (remove-saldo-por-id banco 8 8)
  (transacao banco + 8 10))
