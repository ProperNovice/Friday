package controller;

import card.Card;
import javafx.animation.AnimationTimer;
import numberImageView.NumberImageView;
import numberImageView.NumberImageViewEight;
import numberImageView.NumberImageViewFive;
import numberImageView.NumberImageViewFour;
import numberImageView.NumberImageViewNine;
import numberImageView.NumberImageViewOne;
import numberImageView.NumberImageViewSeven;
import numberImageView.NumberImageViewSix;
import numberImageView.NumberImageViewThree;
import numberImageView.NumberImageViewTwo;
import numberImageView.NumberImageViewZero;
import utils.ArrayList;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;
import utils.HashMap;
import utils.ObjectPool;
import utils.RearrangeTypeEnum;

public enum ListsSizeReaLTime {

	INSTANCE;

	private HashMap<Integer, Class<? extends NumberImageView>> mapNumberImageView = new HashMap<Integer, Class<? extends NumberImageView>>();
	private ContainerImageViewAbles<NumberImageView> deckPlayer, deckHazardKnowledge, deckAging;

	private ListsSizeReaLTime() {

		createMapNumberImageView();
		createContainers();

	}

	private void createContainers() {

		this.deckPlayer = new ContainerImageViewAbles<NumberImageView>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsListSizeIndicators)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesSizeIndicatorDeckPlayer)
						.rearrangeTypeEnum(RearrangeTypeEnum.PIVOT).build());

		this.deckHazardKnowledge = new ContainerImageViewAbles<NumberImageView>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsListSizeIndicators)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesSizeIndicatorDeckHazardKnowledge)
						.rearrangeTypeEnum(RearrangeTypeEnum.PIVOT).build());

		this.deckAging = new ContainerImageViewAbles<NumberImageView>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsListSizeIndicators)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesSizeIndicatorDeckAging)
						.rearrangeTypeEnum(RearrangeTypeEnum.PIVOT).build());

	}

	private void createMapNumberImageView() {

		this.mapNumberImageView.put(0, NumberImageViewZero.class);
		this.mapNumberImageView.put(1, NumberImageViewOne.class);
		this.mapNumberImageView.put(2, NumberImageViewTwo.class);
		this.mapNumberImageView.put(3, NumberImageViewThree.class);
		this.mapNumberImageView.put(4, NumberImageViewFour.class);
		this.mapNumberImageView.put(5, NumberImageViewFive.class);
		this.mapNumberImageView.put(6, NumberImageViewSix.class);
		this.mapNumberImageView.put(7, NumberImageViewSeven.class);
		this.mapNumberImageView.put(8, NumberImageViewEight.class);
		this.mapNumberImageView.put(9, NumberImageViewNine.class);

	}

	public void start() {
		new Update().start();
	}

	private class Update extends AnimationTimer {

		@Override
		public void handle(long now) {

			if (!Flow.INSTANCE.getCurrentGameState().updateListsSizeIndicators())
				return;

			update(deckPlayer, Lists.INSTANCE.deckPlayer);
			update(deckHazardKnowledge, Lists.INSTANCE.deckHazardKnowledge);
			update(deckAging, Lists.INSTANCE.deckAging);

		}

		private void update(ContainerImageViewAbles<NumberImageView> indicatorList,
				ContainerImageViewAbles<? extends Card> cardList) {

			for (NumberImageView numberImageView : indicatorList)
				ObjectPool.INSTANCE.release(numberImageView);

			indicatorList.getArrayList().clear();

			int size = cardList.getSize();
			ArrayList<Integer> integerSize = getNumberList(size);

			for (Integer integer : integerSize)
				indicatorList.getArrayList().addLast(ObjectPool.INSTANCE.acquire(mapNumberImageView.get(integer)));

			indicatorList.relocateImageViews();

			for (NumberImageView numberImageView : indicatorList)
				numberImageView.getImageView().setVisible(true);

		}

		private ArrayList<Integer> getNumberList(int number) {

			ArrayList<Integer> list = new ArrayList<Integer>();

			String listString = Integer.toString(number);

			list.addLast(Integer.parseInt(listString.substring(0, 1)));

			if (number >= 10)
				list.addLast(Integer.parseInt(listString.substring(1, 2)));

			return list;

		}

	}

}
