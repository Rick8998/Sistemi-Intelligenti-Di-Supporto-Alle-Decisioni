# Sistemi-Intelligenti-Di-Supporto-Alle-Decisioni

PROGETTO D’ESAME A.A. 2021/22

L’esame consiste nella risoluzione dei due problemi dettagliati nel seguito.
Per entrambi i problemi occorre
1) costruire il modello tramite Genie
2) realizzare una piccola applicazione che risolva il problema utilizzando le API fornite da Smile

### Problema n.1
Un paziente ha un severo carcinoma al polmone. Il trattamento (T) a cui sottoporlo puo’ essere “tracheotomia” oppure “radiazioni”. Per la decisione e’ importante avere idea delle probabilita’ di Metastasi Mediastinali (MM). Se infatti MM sono assenti la tracheotomia offre una migliore aspettativa di vita, mentre se sono presenti c’e un rischio di morte senza reale beneficio. In particolare, l’aspettativa di vita se effettuo una tracheotomia senza MM e’ di 4.45 anni, mentre e’ di soli 1.8 anni se MM sono presenti. L’aspettativa di vita non cambia se ci sono MM anche se faccio radiazioni, mentre in caso di assenza di MM, le radiazioni portano ad una aspettativa di vita di 2.64 anni.
Attualemente c’e’ una leggera probabilita’ a priori in favore dell’assenza di MM (0.54).
Ci sono pero’ due test possibili per valutare meglio la presenza di MM; il primo e’ una Tomografia Computerizzata detta CT scan; Il secondo e’ una Mediastinoscopia (M Test).
Il CT scan non e’ accurato in quanto ha un falso negativo del 18% ed un falso positivo del 19%. Il secondo test e’ piu’ accurato con sempre il 18% di falso negativo, ma solo lo 0.5% di falso positivo. Il test Mtest e’ pero’ invasivo e puo’ anche causare la morte del paziente; in particolare c’e un incidenza di morte pari allo 0.5%.
Il processo decisionale consiste nel decidere se fare il Ctest, poi nel decidere se eventualmente fare l’MTest e sulla base deigli eventuali risultati decidere il trattamento.
Si noti che la tracheotomia ha una probabilita’ maggiore di causare la morte del paziente, rispetto alle radiazioni, ed in particolare il 3.7% contro il 2%.
Si trascurino i costi delle decisioni (test e trattamento) e si supponga che l’utilita corrisponda all’aspettativa di vita.

### Problema n.2
Un robot (R) deve tenere sotto controllo il movimento di un oggetto mobile (target T) usando sensori sonar e di visione, e usando una mappa dell’ambiente. Scopo del robot e’ restare in prossimita’ del target (target tracking). L’ambiente prevede 4 locazioni o regioni denominate R1, R2, R3, R4 come in figura. Il robot deve restare ad una regione di distanza dal target. Se si trova nella stessa regione e’ “too close”, se si trova a due o piu' regioni di distanza e’ “too far”. Le azioni previste dal robot sono quindi “left L”, “right R”, Stay S”. Modellare il processo decisionale del robot su di un orizzonte temporale prefissato (per es: 4 steps), assumendo che le azioni del robot abbiano una probabilita' di successo del 95% (cioe' nel 5% dei casi l' azione richiesta produce un movimento in una
direzione sbagliata in modo uniforme; es: robot in R2, azione L, con prob. 0.025 rimane in R2, con stessa prob. va in R3 e con prob. 0.95 va correttamente in R1).
Inoltre si consideri il fatto che la posizione del target rilevata dal robot e' tanto meno precisa quanto piu' il robot ed il target sono distanti (es: R in R1 e T in R3 la probabilita' che R capisca che T e' in R3 e' p1; R in R1 e T in R2, la probabilita' di rilevazione corretta del target e' p2>p1)
Si assuma un movimento probabilistico del target nel tempo a piacere.
